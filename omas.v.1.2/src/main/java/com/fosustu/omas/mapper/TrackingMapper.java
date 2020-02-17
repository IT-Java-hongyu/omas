package com.fosustu.omas.mapper;

import com.fosustu.omas.pojo.Tracking;

public interface TrackingMapper {
	
    void insertTrack(Tracking tracking);
    
    Tracking SearchNewest(Tracking tracking);
    
    void UpdateTrack(Tracking tracking); 
}