package de.romjaki.icndbwrapper;

public class RestAction<T, R extends GsonSchematic<T>> {
	
	private final R emptySchematic;
	private final String url;
	private final Map<String, String> parameters;
	
	public RestAction(R emptySchematic, String relativeUrl, Map<String, String> parameters) {
		this.emptySchematic = emptySchematic;
		this.url = relativeUrl;
		this.parameters = parameters;
	}
	
	public void queue() {
		queue(null);
	}
	
	public void queue(Consumer<T> success) {
		queue(success, null);
	}
	
	public void queue(Consumer<T> success, Consumer<T> fail) {
		Thread t = new Thread(new Runnable() {
			@Override
			public void run() {
				Gson gson = new Gson();
				R schematic = gson.fromJson(executeGet(this.url, this.parameters), emptySchematic.getClass());
				//TODO
			}
		});
	}
	
	public T complete() {
		// TODO
	}
	
	public static String executeGet(String url, Map<String, String> parameters) {
		// TODO
	}
	
}
