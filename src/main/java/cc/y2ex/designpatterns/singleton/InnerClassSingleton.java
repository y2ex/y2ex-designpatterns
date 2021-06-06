package cc.y2ex.designpatterns.singleton;

import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * 静态内部类也是懒加载的方式，只有调用InnerClassHandler.instance时才会去加载。同样这种方式也是借助jvm类加载机制，来保证实例的唯一。
 *
 * @author: Yanci丶
 * @date: 2021-06-05
 */
public class InnerClassSingleton implements Serializable {

    private static final long serialVersionUID = 1L;

    private static final Integer NUM = 111;

    private static class InnerClassHandler{
        private static InnerClassSingleton instance = new InnerClassSingleton();
    }

    private InnerClassSingleton(){
//        if (InnerClassHandler.instance != null){
//            throw new RuntimeException("单例不允许实例化多个实例");
//        }
    }

    public static InnerClassSingleton getInstance(){
        return InnerClassHandler.instance;
    }

    Object readResolve() throws ObjectStreamException{
        return InnerClassHandler.instance;
    }

    public static void main(String[] args) {
        InnerClassSingleton instance1 = InnerClassSingleton.getInstance();
        System.out.println(instance1);
        InnerClassSingleton instance2 = InnerClassSingleton.getInstance();
        System.out.println(instance2);
        System.out.println(instance1 == instance2);

//        try {
//            Constructor<InnerClassSingleton> constructor = InnerClassSingleton.class.getDeclaredConstructor();
//            constructor.setAccessible(true);
//            InnerClassSingleton newInstance = constructor.newInstance();
//            System.out.println(newInstance);
//            System.out.println(instance1 == newInstance);
//        } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
//            e.printStackTrace();
//        }
//        InnerClassSingleton instance3 = InnerClassSingleton.getInstance();
//        System.out.println(instance3);

        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("D://File//InnerClassSingleton"));
            oos.writeObject(instance1);
            oos.close();
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("D://File//InnerClassSingleton"));
            InnerClassSingleton object = (InnerClassSingleton) ois.readObject();
            System.out.println(object);
            System.out.println(instance1 == object);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
