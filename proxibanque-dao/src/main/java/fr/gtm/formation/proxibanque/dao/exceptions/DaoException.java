/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.gtm.formation.proxibanque.dao.exceptions;

/**
 *
 * @author Lise Rodier
 */
public class DaoException extends Exception
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public DaoException(String message)
	{
		super(message);
	}

	public DaoException(String message, Throwable cause)
	{
		super(message, cause);
	}

	public DaoException(Throwable cause)
	{
		super(cause);
	}
}
