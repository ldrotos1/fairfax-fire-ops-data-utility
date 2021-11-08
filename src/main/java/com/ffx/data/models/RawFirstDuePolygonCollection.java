package com.ffx.data.models;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * A collection of entities containing polygon coordinates
 * for the first due areas of fire stations.
 * 
 * @author Louis Drotos
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RawFirstDuePolygonCollection {
	private String type;
	private String name;
	private List<RawFirstDuePolygon> features;
}