/*
 * Generated by XDoclet - Do not edit!
 */
package org.dcm4chex.archive.ejb.interfaces;

/**
 * Remote interface for PIXQuery.
 * @xdoclet-generated at ${TODAY}
 * @copyright The XDoclet Team
 * @author XDoclet
 * @version 2.18.0-SNAPSHOT
 */
public interface PIXQuery
   extends javax.ejb.EJBObject
{

   public java.util.List queryCorrespondingPIDs( java.lang.String patientID,java.lang.String issuer,java.lang.String[] domains )
      throws java.rmi.RemoteException;

   public java.util.List queryCorrespondingPIDsByPrimaryPatientID( java.lang.String patientID,java.lang.String issuer,java.lang.String[] domains )
      throws java.rmi.RemoteException;

   public java.util.List queryCorrespondingPIDsByOtherPatientID( java.lang.String patientID,java.lang.String issuer,java.lang.String[] domains )
      throws java.rmi.RemoteException;

}
