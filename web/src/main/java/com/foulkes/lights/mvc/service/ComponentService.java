package com.foulkes.lights.mvc.service;

import com.foulkes.lights.mvc.domain.Components;

import java.util.List;

/**
 * Created by danfoulkes on 03/10/15.
 */
public interface ComponentService {

     List<Components> getAll();
     Components getById(String uniqueId);

}
