package org.example;

import java.util.List;

public interface Role{
    String getNameOfRole();
    List<Permissions> getPermissions();
}
