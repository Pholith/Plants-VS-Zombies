package base;


import java.util.*;

/**
 * 
 */
public class Terrain {


	/*
	 * 
	 */
	public Terrain(int numberOfRows, int squaresByRows, String type, ArrayList<Square> listOfSquares) {
		super();
		if (numberOfRows < 2|| squaresByRows < 2) {
			throw new IllegalArgumentException("Impossible de créé une carte plus petite.");
		}
		this.numberOfRows = numberOfRows;
		this.squaresByRows = squaresByRows;
		this.type = Objects.requireNonNull(type);
		this.listOfSquares = listOfSquares;
		if (squaresByRows*numberOfRows != listOfSquares.size()) {
			throw new IllegalArgumentException("Le nombre de cases ne correspond pas avec les dimensions de la carte");
		}
	}

    private ArrayList<Square> listOfSquares;
	/**
     * 
     */
    private int numberOfRows;

    /**
     * 
     */
    private int squaresByRows;

    /**
     * 
     */
    private String type;


}