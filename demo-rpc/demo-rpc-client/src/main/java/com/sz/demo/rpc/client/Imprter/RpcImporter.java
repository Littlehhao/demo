package com.sz.demo.rpc.client.Imprter;

import com.sz.demo.rpc.service.Exporter.RpcExporter;
import com.sz.demo.rpc.service.server.IDrinkService;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.Socket;

/**
 * @program: demo
 * @description:
 * @author: jie fu
 * @create: 2019-07-27 15:31
 */
public class RpcImporter {

    public static <T> T create(final Class<?> serviceInterface,final String ip,final int port){

        return (T) Proxy.newProxyInstance(RpcImporter.class.getClassLoader(),new Class<?>[]{serviceInterface},new proxyHandler(ip,port,serviceInterface));
    }

    static class proxyHandler implements InvocationHandler {

        private String ip;
        private int port;
        private Class<?> serviceInterface;

        public proxyHandler(String ip, int port, Class<?> serviceInterface) {
            this.ip = ip;
            this.port = port;
            this.serviceInterface = serviceInterface;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

            Socket socket=null;
            ObjectOutputStream output=null;
            ObjectInputStream input=null;

            try {

                //创建socket客户端，根据指定地址连接远程服务提供者
                socket = new Socket(ip, port);
                //将远程服务调用所需的接口类、方法名、参数传递给服务端
                output = new ObjectOutputStream(socket.getOutputStream());
                output.writeUTF(serviceInterface.getName());
                output.writeUTF(method.getName());
                output.writeObject(method.getParameterTypes());
                output.writeObject(args);
                // 同步阻塞等待服务器返回应答，获取应答后返回
                input = new ObjectInputStream(socket.getInputStream());
                return input.readObject();
            }finally {
                if (socket != null) {
                    socket.close();
                }
                if (output != null) {
                    output.close();
                }
                if (input != null) {
                    input.close();
                }
            }
        }
    }


    public static void main(String[] args) {


        for (int i=0;i<200;i++){

            IDrinkService drinkService = RpcImporter.create(IDrinkService.class, "127.0.0.1", 8888);
            System.out.println(drinkService.drinkWater("lilan"));
        }

    }


}
