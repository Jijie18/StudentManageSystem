package sms.Utils;

import sms.beans.*;
import sms.beans.Admin;

import java.lang.Class;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class MapToObjectUtil {
  /**
   * @param map corresponding map(the key of map must be field name in Class)
   * @param cls get the Class type
   * @param <T> T
   * @return an Object that use the data in map
   * @throws Exception throws Exception
   */
  public static <T> T mapToObject(Map<String, Object> map, Class<T> cls) throws Exception {
    T res = cls.newInstance();

    for (Map.Entry<String, Object> entry : map.entrySet()) {
      String colName = entry.getKey();
      Object value = entry.getValue();
      if (value == null) {
        value = "";
      }
      Field field = cls.getDeclaredField(colName);
      if (field.getType() == int.class) {
        if (value == "")
          value = 0;
        else {
          value = Integer.parseInt(value.toString());
        }
      }
      field.setAccessible(true);
      field.set(res, value);
    }
    return res;
  }

  public static void main(String[] args) throws Exception {
    Map<String, Object> map = new HashMap<>();
    map.put("admin_id", 1);
    map.put("name", "peng");
    map.put("password", "123");

    Admin admin = mapToObject(map, Admin.class);

    System.out.println(admin.getAdmin_id());
    System.out.println(admin.getName());
    System.out.println(admin.getPassword());

  }
}
