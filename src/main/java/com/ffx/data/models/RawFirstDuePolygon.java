package com.ffx.data.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * An entity class containing the polygon coordinates 
 * for a station's first due area
 * 
 * @author Louis Drotos
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RawFirstDuePolygon {
	private int station;
	private String coordinates;
}
