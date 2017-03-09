package com.asyraf.frilo.data.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("com.robohorse.robopojogenerator")
public class ParkLocationResponse{

	@SerializedName("data")
	@Expose
	public List<DataItem> data;

	@SerializedName("message")
	@Expose
	public String message;

	@SerializedName("status")
	@Expose
	public int status;

	@Override
 	public String toString(){
		return 
			"ParkLocationResponse{" + 
			"data = '" + data + '\'' + 
			",message = '" + message + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}