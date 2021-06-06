package cc.y2ex.designpatterns.singleton;

import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * @author: Yanci丶
 * @date: 2021-06-05
 */
public enum EnumSingleton {
    INSTANCE;

    public static void main(String[] args) {
        EnumSingleton instance1 = EnumSingleton.INSTANCE;
        System.out.println(instance1);
        EnumSingleton instance2 = EnumSingleton.INSTANCE;
        System.out.println(instance2);
        System.out.println(instance1 == instance2);

//        try {
//            Constructor<EnumSingleton> constructor = EnumSingleton.class.getDeclaredConstructor(String.class, int.class);
//            constructor.setAccessible(true);
//            EnumSingleton newInstance = constructor.newInstance("INSTANCE", 0);
//            System.out.println(newInstance);
//            System.out.println(instance1 == newInstance);
//        } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
//            e.printStackTrace();
//        }

        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("D://File//EnumSingleton"));
            oos.writeObject(instance1);
            oos.close();
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("D://File//EnumSingleton"));
            EnumSingleton object = (EnumSingleton) ois.readObject();
            System.out.println(object);
            System.out.println(instance1 == object);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
