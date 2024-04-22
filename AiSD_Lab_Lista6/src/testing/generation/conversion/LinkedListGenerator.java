package testing.generation.conversion;

import java.util.LinkedList;

import testing.MarkedValue;
import testing.generation.Generator;

public class LinkedListGenerator<T> implements Generator<MarkedValue<T>> {

	private Generator<? extends T> generator;
	
	public LinkedListGenerator(Generator<? extends T> generator) {
		this.generator = generator;
	}
	
	@Override
	public LinkedList<MarkedValue<T>> generate(int size) {
		LinkedList<MarkedValue<T>> list = new LinkedList<>();

		MarkedValue.clearMarkers();
		for(T value : generator.generate(size)) {
			list.add(new MarkedValue<T>(value));
		}
		
		return list;
	}
}
