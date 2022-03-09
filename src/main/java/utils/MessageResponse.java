package utils;


import lombok.Data;

@Data
public class MessageResponse {
	
	String message;
	Object response;
	boolean error;

}
