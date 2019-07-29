package com.sz.demo.rpc.service.Exporter;

import com.sz.demo.rpc.service.server.DrinkServiceImpl;
import com.sz.demo.rpc.service.server.IDrinkService;
import sun.util.locale.provider.AvailableLanguageTags;

import javax.sound.sampled.Port;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * @program: demo
 * @description:
 * @author: jie fu
 * @create: 2019-07-27 14:42
 */
public class RpcExporter implements IRpcExporter {

    private int port;

    private static final ConcurrentHashMap<String,Class> map=new ConcurrentHashMap();

    //定义一个线程池 接收客户端请求
    static Executor executors=Executors.newFixedThreadPool(20);

    public RpcExporter(int port) {
        this.port = port;
    }

    /**
     * 服务启动
     * @throws Exception
     */
    @Override
    public void start() throws Exception {

        ServerSocket socket = new ServerSocket();
        socket.bind(new InetSocketAddress(port));
        System.out.println("bio rpc 服务端启动 端口为"+port);

        try {

            while (true){
                System.out.println("等待客户端进入");
                executors.execute(new ServersTask(socket.accept()));
            }

        }catch (Exception e){
            if(socket != null){
                socket.close();
            }
        }


    }

    /**
     * 服务注册
     * @param inter 服务接口
     * @param impl  服务实现类
     */
    @Override
    public void register(Class inter, Class impl) {
        map.put(inter.getName(),impl);
        System.out.println("服务注册完成");
    }


    /**
     * 静态内部类 实现服务端的rpc核心代码实现
     */
    static class ServersTask implements Runnable{

        Socket client=null;

        public ServersTask(Socket client) {
            this.client = client;
        }

        //输入流
        ObjectInputStream input=null;
        //输出流
        ObjectOutputStream output=null;

        @Override
        public void run() {

            //接收客户端发送来的字节流，并转换成对象，反射调用服务实现者，获取执行结果
            try {
                input = new ObjectInputStream(this.client.getInputStream());
                String interfaceName = input.readUTF();
                String methodName = input.readUTF();
                Class<?>[] parameterType = (Class<?>[]) input.readObject();
                Object[] args = (Object[]) input.readObject();
                Class serverClass = map.get(interfaceName);
                if (serverClass == null) {
                    throw new ClassNotFoundException(interfaceName + " not found");
                }
                Method method = serverClass.getMethod(methodName, parameterType);
                Object result = method.invoke(serverClass.newInstance(), args);
                //执行结果反序列化返回给客户端
                output = new ObjectOutputStream(this.client.getOutputStream());
                output.writeObject(result);


            }catch (Exception e){
                e.printStackTrace();
            }finally {
                if (output != null) {
                    try {
                        output.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (input != null) {
                    try {
                        input.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (this.client != null) {
                    try {
                        this.client.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }



        }
    }


    public static void main(String[] args) {

        IDrinkService drinkService = new DrinkServiceImpl();
        try {

            RpcExporter rpcExporter = new RpcExporter(8888);
            rpcExporter.register(IDrinkService.class,DrinkServiceImpl.class);
            rpcExporter.start();
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
