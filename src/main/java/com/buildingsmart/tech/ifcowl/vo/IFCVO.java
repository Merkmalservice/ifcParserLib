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

import java.util.*;

public class IFCVO {

    private Long lineNum;
    private String fullLineAfterNum;
    private String name = null;
    private List<Object> list = null;
    private Map<String, List<IFCVO>> inversePointerSets = null;

    public IFCVO() {
        //default constructor
    }

    public String getFullLineAfterNum() {
        return fullLineAfterNum;
    }

    public void setFullLineAfterNum(String fullLineAfterNum) {
        this.fullLineAfterNum = fullLineAfterNum;
    }

    public Long getLineNum() {
        return lineNum;
    }

    public void setLineNum(Long lineNum) {
        this.lineNum = lineNum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Object> getObjectList() {
        if (list == null){
            synchronized (this){
                if (list == null) {
                    this.list = new ArrayList<>();
                }
            }
        }
        return list;
    }

    public void setList(List<Object> list) {
        this.list = list;
    }

    public Map<String, List<IFCVO>> getInversePointerSets() {
        if (inversePointerSets == null){
            synchronized (this){
                if (inversePointerSets == null){
                    this.inversePointerSets = new HashMap<>();
                }
            }
        }
        return inversePointerSets;
    }

    public void setInversePointerSets(Map<String, List<IFCVO>> inversePointerSets) {
        this.inversePointerSets = inversePointerSets;
    }
}