package ie.app.com;

import java.util.Map;

public class ExtensionCategory {

	private Map<String, String> extensionToCategory;
	
	public ExtensionCategory() {
        initializeExtensionMap();
	}
	
	private void initializeExtensionMap(){
	    	
	    }
	
	public Map<String, String> getExtensionMap(){
		return extensionToCategory;
	}
}
