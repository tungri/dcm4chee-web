/*
 * Generated by XDoclet - Do not edit!
 */
package org.dcm4chex.archive.ejb.interfaces;

/**
 * Home interface for UPSManager.
 * @xdoclet-generated at ${TODAY}
 * @copyright The XDoclet Team
 * @author XDoclet
 * @version 2.18.0-SNAPSHOT
 */
public interface UPSManagerHome
   extends javax.ejb.EJBHome
{
   public static final String COMP_NAME="java:comp/env/ejb/UPSManager";
   public static final String JNDI_NAME="ejb/UPSManager";

   public org.dcm4chex.archive.ejb.interfaces.UPSManager create()
      throws javax.ejb.CreateException,java.rmi.RemoteException;

}
