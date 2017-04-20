package saves;


import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Home on 4/20/2017.
 */
public class Save {

    public static void exportSave(HashMap<Integer, Integer> items, boolean hasArmor, int health, int currentHealth) throws IOException {
        String path = System.getProperty("user.dir") + "\\save.ser";
        SaveObject saveObject = new SaveObject(items, hasArmor, health, currentHealth);
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(path))) {
            oos.writeObject(saveObject);
            System.out.println("success");
        } catch (NotSerializableException e) {
            e.getCause().printStackTrace();
        }
    }

    public static SaveObject importSave(){
        String path = System.getProperty("user.dir") + "\\save.ser";
        SaveObject object = null;
        try (ObjectInputStream oos = new ObjectInputStream(new FileInputStream(path))) {
            object = (SaveObject) oos.readObject();

        } catch (NotSerializableException e) {
            e.getCause().printStackTrace();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return object;
    }
}
