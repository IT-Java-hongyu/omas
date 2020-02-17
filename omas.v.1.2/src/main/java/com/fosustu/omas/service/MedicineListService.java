package com.fosustu.omas.service;

import java.util.List;

import com.fosustu.omas.pojo.MedicineTime;
import com.fosustu.omas.pojo.MedicineTimeInput;

public interface MedicineListService {
    List<MedicineTime> getMedicineTime(MedicineTimeInput mt);
}
