package com.asyraf.frilo.data.model;

import com.google.gson.annotations.Expose;
import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

@Generated("com.robohorse.robopojogenerator")
public class DataItem implements Serializable {

	@SerializedName("location_name")
	@Expose
	public String locationName;

	@SerializedName("latitude")
	@Expose
	public double latitude;

	@SerializedName("location_desc")
	@Expose
	public String locationDesc;

	@SerializedName("id")
	@Expose
	public int id;

	@SerializedName("longitude")
	@Expose
	public double longitude;

	@Override
 	public String toString(){
		return 
			"DataItem{" + 
			"location_name = '" + locationName + '\'' + 
			",latitude = '" + latitude + '\'' + 
			",location_desc = '" + locationDesc + '\'' + 
			",id = '" + id + '\'' + 
			",longitude = '" + longitude + '\'' + 
			"}";
		}
}