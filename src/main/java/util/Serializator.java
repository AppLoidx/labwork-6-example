package util;

import java.io.*;


public class Serializator {
    public static byte[] serialize(Serializable obj) throws IOException {
        ByteArrayOutputStream ba = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(ba);
        oos.writeObject(obj);
        oos.close();
        return ba.toByteArray();
    }
    public static Object deserialize(byte[] bytes) throws IOException, ClassNotFoundException {
        return new ObjectInputStream(new ByteArrayInputStream(bytes)).readObject();
    }
}
