/* ***** BEGIN LICENSE BLOCK *****
 * Version: MPL 1.1/GPL 2.0/LGPL 2.1
 *
 * The contents of this file are subject to the Mozilla Public License Version
 * 1.1 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 * http://www.mozilla.org/MPL/
 *
 * Software distributed under the License is distributed on an "AS IS" basis,
 * WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License
 * for the specific language governing rights and limitations under the
 * License.
 *
 * The Original Code is part of dcm4che, an implementation of DICOM(TM) in
 * Java(TM), hosted at http://sourceforge.net/projects/dcm4che.
 *
 * The Initial Developer of the Original Code is
 * Agfa Healthcare.
 * Portions created by the Initial Developer are Copyright (C) 2006
 * the Initial Developer. All Rights Reserved.
 *
 * Contributor(s):
 * See listed authors below. 
 *
 * Alternatively, the contents of this file may be used under the terms of
 * either the GNU General Public License Version 2 or later (the "GPL"), or
 * the GNU Lesser General Public License Version 2.1 or later (the "LGPL"),
 * in which case the provisions of the GPL or the LGPL are applicable instead
 * of those above. If you wish to allow use of your version of this file only
 * under the terms of either the GPL or the LGPL, and not to allow others to
 * use your version of this file under the terms of the MPL, indicate your
 * decision by deleting the provisions above and replace them with the notice
 * and other provisions required by the GPL or the LGPL. If you do not delete
 * the provisions above, a recipient may use your version of this file under
 * the terms of any one of the MPL, the GPL or the LGPL.
 *
 * ***** END LICENSE BLOCK ***** */

package org.dcm4chex.archive.tools;

import java.security.MessageDigest;

import org.dcm4che.util.Base64;

/**
 * @author Gunter Zeilinger <gunterze@gmail.com>
 * @version $Id: Pwd2Hash.java 3122 2007-02-15 17:35:26Z gunterze $
 * @since Feb 15, 2007
 */
public class Pwd2Hash {

    public static void main(String[] args) {
        if ( args.length < 1 ) {
            System.out.println("USAGE: Pwd2Hash <readable password>");
        } else {
            try {
                String hash = Base64.byteArrayToBase64(createHash(args[0]));
                System.out.println("Hashed password:"+hash);
            } catch (Exception x) {
                System.out.println("Cant create SHA1 hash"+x.getMessage());
                x.printStackTrace();
            }
        }
    }

    public static final byte[] createHash(String value) throws Exception
    {
           final MessageDigest digest = MessageDigest.getInstance("SHA");
           byte[] hashBytes = digest.digest((value).getBytes("UTF-8"));
           return hashBytes;
    }
    
}
