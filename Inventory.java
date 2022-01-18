import java.util.ArrayList;

public class Inventory<UserObject>{
  private ArrayList<ObjectWrapper<UserObject>> items;

  private static class ObjectWrapper<UserObject>{
    UserObject item;
    String itemName;

    ObjectWrapper(String itemName, UserObject item){
      this.item = item;
      this.itemName = itemName;
    }
  }

  void add(String itemName, UserObject item) throws ItemNameAlreadyExistsException {
    itemName = itemName.trim();
    Boolean itemHasBeenAdded = false;
    if (items.isEmpty()){
      items.add(new ObjectWrapper<UserObject>(itemName, item));
      itemHasBeenAdded = true;
    } else {
      int size = items.size();
      for (int i = 0; i < size; i++){
        ObjectWrapper currentNode = items.get(i);
        if (itemName.equalsIgnoreCase(currentNode.itemName)){
          throw new ItemNameAlreadyExistsException("Item name "+itemName+" is already being used.");
        } else if (itemName == firstAlphabetically(itemName, currentNode.itemName)){
          items.add(i, new ObjectWrapper<UserObject>(itemName, item));
          itemHasBeenAdded = true;
          break;
        } //else: evaluate alphabetical order of itemName against next item in list
      }
      //if itemName should be at the end of the list
      if(!itemHasBeenAdded){
        items.add(items.size(), new ObjectWrapper<UserObject>(itemName, item));
      }
    }
  }

  UserObject retrieve(String itemName) throws ItemNotFoundException {
    itemName = itemName.trim();
    for(ObjectWrapper<UserObject> node: items){
      if(itemName.equalsIgnoreCase(node.itemName)){
        //cast causes compiler warning but there's no possible way for node.item to not be a UserObject
        return (UserObject)node.item;
      }
    }
    //if none of the itemnames matched what was entered.
    throw new ItemNotFoundException("Item "+itemName+" not found in this Inventory.");
  }

  void remove(String itemName) throws ItemNotFoundException {
    itemName = itemName.trim();
    ObjectWrapper<UserObject> toRemove = null;

    for(ObjectWrapper node: items){
      if(node.itemName.equalsIgnoreCase(itemName)){
        toRemove = node;
      }
    }
    if(toRemove == null){
      throw new ItemNotFoundException("Item "+itemName+" not found in this Inventory.");
    } else {
      items.remove(toRemove);
    }
  }

  void rename(String oldItemName, String newItemName) throws ItemNotFoundException, ItemNameAlreadyExistsException {
    oldItemName = oldItemName.trim();
    newItemName = newItemName.trim();

    //chain of exceptions ensures first that oldItemName exists, then that newItemName does not already exist
    add(newItemName, retrieve(oldItemName));
    remove(oldItemName);
  }

  String[] currentItemNamesArray(){
    String[] itemNamesArray = new String[items.size()];
    for (int i = 0; i < items.size(); i++){
      itemNamesArray[i] = items.get(i).itemName;
    }
    return itemNamesArray;
  }

  private String firstAlphabetically(String s1, String s2){
    char[] s1CharArray = s1.trim().toLowerCase().toCharArray();
    char[] s2CharArray = s2.trim().toLowerCase().toCharArray();

    for (int i = 0; i < s2.length() && i < s1.length(); i++){
      if((int)s1CharArray[i]>(int)s2CharArray[i]){
        return s2;
      } else if ((int)s2CharArray[i]>(int)s1CharArray[i]){
        return s1;
      }
      //else: next letter must be examined
    }
    //you only get here if either of the two strings were idenctical up to
    //the end of the other string. We favor the shorter string and return
    //null if they are identical.
    if(s1.length()<s2.length()){
      return s1;
    } else if(s2.length()<s1.length()){
      return s2;
    } else {
      return null;
    }
  }

  Inventory(){
    items = new ArrayList<ObjectWrapper<UserObject>>();
  }
}
