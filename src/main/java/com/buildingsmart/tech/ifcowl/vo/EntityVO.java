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
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class EntityVO implements Serializable {
    private static final long serialVersionUID = -3172893316956350157L;
    private String name;
    private List<AttributeVO> attributes;
    private List<InverseVO> inverses;

    private List<AttributeVO> derivedAttributeList;
    private List<InverseVO> derivedInverseList;

    private String superclass;
    private boolean abstractsuperclass = false;
    private List<TypeVO> parentSelect;
    private Set<String> subClassList;
    private static ConcurrentHashMap<String, EntityVO> entities = new ConcurrentHashMap<>(100);

    public EntityVO() {
        //default constructor
    }

    public EntityVO(String name) {
        super();
        this.name = name;
        entities.put(name.toLowerCase(), this);
    }

    public static EntityVO getEntityVO(String entityName) {
        return entities.computeIfAbsent(entityName.toLowerCase(), EntityVO::new);
    }

    public List<InverseVO> getDerivedInverseList() {
        if (this.derivedInverseList == null){
            synchronized (this){
                if (this.derivedInverseList == null){
                    this.derivedInverseList = new ArrayList<>();
                }
            }
        }
        return derivedInverseList;
    }

    public void setDerivedInverseList(List<InverseVO> derivedInverseList) {
        this.derivedInverseList = derivedInverseList;
    }

    public List<InverseVO> getInverses() {
        if (this.inverses == null){
            synchronized (this){
                if (this.inverses == null){
                    this.inverses = new ArrayList<>();
                }
            }
        }
        return inverses;
    }

    public void setInverses(List<InverseVO> inverses) {
        this.inverses = inverses;
    }

    public List<AttributeVO> getDerivedAttributeList() {
        if (this.derivedAttributeList == null){
            synchronized (this){
                if (this.derivedAttributeList == null){
                    this.derivedAttributeList = new ArrayList<>();
                }
            }
        }
        return derivedAttributeList;
    }

    public void setDerivedAttributeList(List<AttributeVO> derivedList) {
        this.derivedAttributeList = derivedList;
    }

    public List<AttributeVO> getAttributes() {
        if (this.attributes == null){
            synchronized (this){
                if (this.attributes == null){
                    this.attributes = new ArrayList<>();
                }
            }
        }
        return attributes;
    }

    public void setAttributes(List<AttributeVO> attributes) {
        this.attributes = attributes;
    }

    public String getSuperclass() {
        return superclass;
    }

    public void setSuperclass(String superclass) {
        this.superclass = superclass;
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

    public void setAbstractSuperclass(boolean abstractsuperclass) {
        this.abstractsuperclass = abstractsuperclass;
    }

    public boolean isAbstractSuperclass() {
        return abstractsuperclass;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "EntityVO [name=" + name + ", attributes=" + attributes + ", inverses=" + inverses + ", derived_attribute_list=" + derivedAttributeList + ", derived_inverse_list="
                        + derivedInverseList + ", superclass=" + superclass + ", abstractsuperclass=" + abstractsuperclass + "]";
    }

    /**
     * @return the subClassList
     */
    public Set<String> getSubClassList()
    {
        if (this.subClassList == null){
            synchronized (this){
                if (this.subClassList == null){
                    this.subClassList = new HashSet<>();
                }
            }
        }
        return subClassList;
    }

    /**
     * @param subClassList
     *            the subClassList to set
     */
    public void setSubClassList(Set<String> subClassList) {
        this.subClassList = subClassList;
    }
}
