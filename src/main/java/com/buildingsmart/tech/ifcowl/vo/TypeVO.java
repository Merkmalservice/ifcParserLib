/*
 Copyright (c) 2014 Jyrki Oraskari, Aalto University (jyrki [dot] oraskari [at] aalto [dot] fi)
 Copyright (c) 2014 Pieter Pauwels, Ghent University (modifications - pipauwel [dot] pauwels [at] ugent [dot] be / pipauwel [at] gmail [dot] com)
 Copyright (c) 2016 Lewis John McGibbney, Apache (mavenized - lewismc [at] apache [dot] org)

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
 */

package com.buildingsmart.tech.ifcowl.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class TypeVO implements Serializable {
    private static final long serialVersionUID = -3366648676376786356L;
    private String name;
    private String primarytype;
    private List<String> selectEntities;
    private List<String> enumEntities;
    private List<TypeVO> parentSelect;
    private int[] listCardinalities = new int[2];
    private static final ConcurrentHashMap<String, TypeVO> types = new ConcurrentHashMap<>(100);
    private static final Set<String> typeNamesLowerCase = ConcurrentHashMap.newKeySet();

    public TypeVO(String name) {
        super();
        this.name = name;
        this.primarytype = name;
        types.put(name, this);
        typeNamesLowerCase.add(name.toLowerCase());
    }

    public TypeVO(String name, String primarytype) {
        super();
        this.name = name;
        this.primarytype = primarytype;
        types.put(name, this);
        typeNamesLowerCase.add(name.toLowerCase());
    }

    public static TypeVO getTypeVO(String typeName) {
        return types.computeIfAbsent(typeName, TypeVO::new);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getSelectEntities() {
        if (this.selectEntities == null){
            synchronized (this){
                if (this.selectEntities == null){
                    this.selectEntities = new ArrayList<>();
                }
            }
        }
        return this.selectEntities;
    }

    public void setSelectEntities(List<String> selectEntities) {
        this.selectEntities = selectEntities;
    }

    public List<TypeVO> getParentSelectTypes() {
        if (this.parentSelect == null){
            synchronized (this){
                if (this.parentSelect == null){
                    this.parentSelect = new ArrayList<>();
                }
            }
        }
        return parentSelect;
    }

    public void addParentSelectType(TypeVO parentSelect) {
        if (this.parentSelect == null)
            this.parentSelect = new ArrayList<>();
        this.parentSelect.add(parentSelect);
    }

    public String getPrimarytype() {
        return primarytype;
    }

    public void setPrimarytype(String primarytype) {
        this.primarytype = primarytype;
    }

    public List<String> getEnumEntities() {
        if (this.enumEntities == null){
            synchronized (this){
                if (this.enumEntities == null){
                    this.enumEntities = new ArrayList<>();
                }
            }
        }
        return enumEntities;
    }

    public void setEnumEntities(List<String> enumEntities) {
        this.enumEntities = enumEntities;
    }

    public int[] getListCardinalities() {
        return listCardinalities;
    }

    public void setListCardinalities(int[] listCardinalities) {
        this.listCardinalities = listCardinalities;
    }

    public static boolean checkIfType(String ptype) {
        return typeNamesLowerCase.contains(ptype.toLowerCase());
    }

    @Override
    public String toString() {
        return "TypeVO [name=" + name + ", primarytype=" + primarytype + ", select_entities=" + selectEntities + ", enum_entities=" + enumEntities + "]";
    }
}