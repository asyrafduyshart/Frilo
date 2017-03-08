package com.asyraf.frilo.data.model;

import com.google.gson.annotations.Expose;
import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("com.robohorse.robopojogenerator")
public class Response{

	@SerializedName("message")
	@Expose
	public String message;

	@SerializedName("status")
	@Expose
	public int status;
}