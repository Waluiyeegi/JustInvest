package org.example;

import java.util.List;

//Role interface that wil allow you to get the name of the role as well as the lsit of permissions
public interface Role{
    String getNameOfRole();
    List<Permissions> getPermissions();
}
