package cc.y2ex.designpatterns.singleton;

import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * @author: Yanci丶
 * @date: 2021-06-05
 */
public class LazySingleton implements Serializable{

    private static final long serialVersionUID = 1L;

    /**
     * volatile在这里作用是防止指令重排
     */
    private volatile static LazySingleton instance;

    private LazySingleton(){
//        if (instance != null){
//            throw new RuntimeException("单例不允许实例化多个对象");
//        }
    }

    public static LazySingleton getInstance(){
        if (instance == null){
            synchronized (LazySingleton.class){
                if (instance == null){
                    instance = new LazySingleton();
                }
            }
        }
        return instance;
    }

    Object readResolve() throws ObjectStreamException{
        return instance;
    }

    public static void main(String[] args) {
        LazySingleton instance1 = LazySingleton.getInstance();
        System.out.println(instance1);
        LazySingleton instance2 = LazySingleton.getInstance();
        System.out.println(instance2);
        System.out.println(instance1 == instance2);

        // 通过反射获取实例
//        try {
//            Constructor<LazySingleton> constructor = LazySingleton.class.getDeclaredConstructor();
//            constructor.setAccessible(true);
//            LazySingleton lazySingleton = constructor.newInstance();
//            System.out.println(instance1 == lazySingleton);
//            System.out.println(lazySingleton);
//        } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
//            e.printStackTrace();
//        }
//        LazySingleton instance3 = LazySingleton.getInstance();
//        System.out.println(instance3);

        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("D://File//LazySingleton"));
            oos.writeObject(instance1);
            oos.close();
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("D://File//LazySingleton"));
            LazySingleton object = (LazySingleton) ois.readObject();
            System.out.println(object);
            System.out.println(instance1 == object);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
