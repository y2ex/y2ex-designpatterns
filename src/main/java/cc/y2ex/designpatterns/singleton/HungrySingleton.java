package cc.y2ex.designpatterns.singleton;

import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * 饿汉模式是借助jvm类加载机制，来保证实例的唯一。
 *
 * @author: Yanci丶
 * @date: 2021-06-05
 */
public class HungrySingleton implements Serializable{

    private static final long serialVersionUID = 1L;

    private final static HungrySingleton instance = new HungrySingleton();

    private HungrySingleton(){
//        if (instance != null){
//            throw new RuntimeException("单例不允许实例化多个实例");
//        }
    }

    public static HungrySingleton getInstance() {
        return instance;
    }

    Object readResolve() throws ObjectStreamException{
        return instance;
    }

    public static void main(String[] args) {
        HungrySingleton instance1 = HungrySingleton.getInstance();
        System.out.println(instance1);
        HungrySingleton instance2 = HungrySingleton.getInstance();
        System.out.println(instance2);
        System.out.println(instance1 == instance2);

//        try {
//            Constructor<HungrySingleton> constructor = HungrySingleton.class.getDeclaredConstructor();
//            constructor.setAccessible(true);
//            HungrySingleton newInstance = constructor.newInstance();
//            System.out.println(newInstance);
//            System.out.println(instance1 == newInstance);
//        } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
//            e.printStackTrace();
//        }
//        HungrySingleton instance3 = HungrySingleton.getInstance();
//        System.out.println(instance3);

        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("D://File//HungrySingleton"));
            oos.writeObject(instance1);
            oos.close();
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("D://File//HungrySingleton"));
            HungrySingleton object = (HungrySingleton) ois.readObject();
            System.out.println(object);
            System.out.println(instance1 == object);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}