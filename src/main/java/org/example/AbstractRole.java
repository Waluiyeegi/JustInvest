package org.example;

import java.util.ArrayList;
import java.util.List;

abstract class AbstractRole implements Role{
    protected List<Permissions> permissions = new ArrayList<>();
    public List<Permissions> getPermissions(){
        return permissions;
    }
}
