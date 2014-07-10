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
 * Agfa-Gevaert AG.
 * Portions created by the Initial Developer are Copyright (C) 2008
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

package org.dcm4chee.web.webview.weasis;

import java.net.URL;
import java.util.Properties;

import org.dcm4chee.web.common.webview.link.spi.WebviewerLinkProviderSPI;

/**
 * @author Franz Willer <franz.willer@gmail.com>
 * @version $Revision$ $Date$
 * @since May 15, 2010
 */
public class WeasisLinkProvider extends WebviewerLinkProviderSPI {
    private static final long serialVersionUID = 4548297230882756086L;

    private static String baseUrl = "/weasis-pacs-connector/viewer.jnlp?";

    private String encryptKey;

    @Override
    public String getName() {
        return "weasis";
    }

    @Override
    public void setBaseURL(String baseUrl) {
        if (baseUrl != null) {
            WeasisLinkProvider.baseUrl = baseUrl;
            if (WeasisLinkProvider.baseUrl.indexOf("?") == -1) {
                WeasisLinkProvider.baseUrl += "?";
            }
        }
        try {
            URL config = this.getClass().getResource("/weasis-pacs-connector.properties");
            if (config != null) {
                Properties pacsProperties = new Properties();
                pacsProperties.load(config.openStream());
                encryptKey = pacsProperties.getProperty("encrypt.key", null);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean supportPatientLevel() {
        return true;
    }

    @Override
    public boolean supportStudyLevel() {
        return true;
    }

    @Override
    public boolean supportSeriesLevel() {
        return true;
    }

    @Override
    public boolean supportInstanceLevel() {
        return true;
    }

    @Override
    public boolean supportPresentationState() {
        return false;
    }

    @Override
    public boolean supportKeySelectionObject() {
        return false;
    }

    @Override
    public boolean supportStructuredReport() {
        return false;
    }

    @Override
    public String getUrlForPatient(String patientId, String issuer) {
        StringBuilder buffer = new StringBuilder(baseUrl);
        if (encryptKey == null) {
            buffer.append("patientID=");
            buffer.append(patientId);
            if (issuer != null) {
                buffer.append("%5E%5E%5E");
                buffer.append(issuer);
            }
        } else {
            buffer.append("patientID=");
            String message = issuer == null ? patientId : patientId + "%5E%5E%5E" + issuer;
            buffer.append(EncryptUtils.encrypt(message, encryptKey));
        }
        return buffer.toString();
    }

    @Override
    public String getUrlForStudy(String studyIuid) {
        StringBuilder buffer = new StringBuilder(baseUrl);
        buffer.append("studyUID=");
        buffer.append(encryptKey == null ? studyIuid : EncryptUtils.encrypt(studyIuid, encryptKey));
        return buffer.toString();
    }

    @Override
    public String getUrlForSeries(String seriesIuid) {
        StringBuilder buffer = new StringBuilder(baseUrl);
        buffer.append("seriesUID=");
        buffer.append(encryptKey == null ? seriesIuid : EncryptUtils.encrypt(seriesIuid, encryptKey));
        return buffer.toString();
    }

    @Override
    public String getUrlForInstance(String sopIuid) {
        StringBuilder buffer = new StringBuilder(baseUrl);
        buffer.append("objectUID=");
        buffer.append(encryptKey == null ? sopIuid : EncryptUtils.encrypt(sopIuid, encryptKey));
        return buffer.toString();
    }

    @Override
    public String getUrlForPresentationState(String iuid) {
        return null;
    }

    @Override
    public String getUrlForKeyObjectSelection(String iuid) {
        return null;
    }

    @Override
    public String getUrlForStructuredReport(String arg0) {
        return null;
    }

}
