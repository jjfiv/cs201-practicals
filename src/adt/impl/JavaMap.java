package adt.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import adt.ListADT;
import adt.MapADT;
import adt.Pair;

/**
 * This is a MapADT built out of a JavaMap.
 * 
 * @author jfoley
 *
 * @param <K> - the key type.
 * @param <V> - the value type.
 */
public class JavaMap<K, V> extends MapADT<K, V> {
	/**
	 * The private Java hashmap that does all the work.
	 */
	private LinkedHashMap<K, V> data;

	/**
	 * Create an empty MapADT.
	 */
	public JavaMap() {
		this.data = new LinkedHashMap<K, V>();
	}

	/**
	 * Create a MapADT copied from a Java Map.
	 * 
	 * @param toCopy - the regular Java Map to copy.
	 */
	public JavaMap(Map<K, V> toCopy) {
		this.data = new LinkedHashMap<K, V>(toCopy);
	}

	@Override
	public void put(K k, V v) {
		this.data.put(k, v);
	}

	@Override
	public V get(K k) {
		return this.data.get(k);
	}

	@Override
	public int size() {
		return this.data.size();
	}

	@Override
	public V remove(K k) {
		return this.data.remove(k);
	}

	@Override
	public ListADT<K> getKeys() {
		return new JavaList<>(new ArrayList<>(this.data.keySet()));
	}

	@Override
	public ListADT<Pair<K, V>> getEntries() {
		JavaList<Pair<K, V>> output = new JavaList<>();
		for (Map.Entry<K, V> entry : this.data.entrySet()) {
			output.addBack(new Pair<>(entry.getKey(), entry.getValue()));
		}
		return output;
	}

}
