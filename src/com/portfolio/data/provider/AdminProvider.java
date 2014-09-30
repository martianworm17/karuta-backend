/* =======================================================
	Copyright 2014 - ePortfolium - Licensed under the
	Educational Community License, Version 2.0 (the "License"); you may
	not use this file except in compliance with the License. You may
	obtain a copy of the License at

	http://www.osedu.org/licenses/ECL-2.0

	Unless required by applicable law or agreed to in writing,
	software distributed under the License is distributed on an "AS IS"
	BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
	or implied. See the License for the specific language governing
	permissions and limitations under the License.
   ======================================================= */

package com.portfolio.data.provider;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import javax.activation.MimeType;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * @author vassoill
 * Le dataProvider est la classe d'acc�s aux donn�es.
 * Voici l'interface qui d�finit toutes les m�thodes possibles d'un dataProvider.
 * Ces m�thodes sont directement inspir�es des appels REST existants
 *  */
public interface AdminProvider
{
	/**
	 * Constructeur
	 */
	public void dataProvider();

	public void disconnect();

	public void connect(Properties connectionProperties) throws Exception;

	Connection getConnection();


	/**
	 * @param porfolioId Id du portfolio
	 * @param semtag Filtre sur le tag semantique
	 * @param parentUuid Filtre sur l'Uuid parent
	 * @param filterId Id du filtre personnalis�
	 * @param filterParameters Si filtre personnalis�, param�tres du filtre personnalis�
	 * @param sortId Id du tri personnalis�
	 * @return une chaine XML
	 */
	public void writeLog(String url, String method, String headers, String inBody, String outBody, int code);

	/// Relatif � l'authentification
	public String[] postCredential(String login, String password, Integer userId) throws ServletException, IOException;
	public String[] postCredentialFromXml(String xml, Integer userId) throws ServletException, IOException;
	public void getCredential(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
	public String getMysqlUserUid (String login) throws Exception;
	public String getUserUidByTokenAndLogin(String login, String token) throws Exception;
	public int deleteCredential(int userId);
	public boolean isAdmin( String uid );

	/// Relatif aux portfolios
	public Object getPortfolio(MimeType outMimeType, String portfolioUuid, int userId, int groupId, String label, String resource, String files) throws Exception;
	public Object getPortfolios(MimeType outMimeType,int userId,int groupId,Boolean portfolioActive) throws Exception;
	public Object getPortfolioByCode(MimeType mimeType, String portfolioCode, int userId, int groupId, String resources) throws Exception;
	public String getPortfolioUuidByNodeUuid(String nodeUuid) throws Exception ;
	public Object getPortfolioZip(MimeType mimeType, String portfolioUuid, int userId, int groupId, String label, Boolean resource, Boolean files) throws Exception;

	public Object putPortfolio(MimeType inMimeType,MimeType outMimeType,String in, String portfolioUuid, int userId, Boolean portfolioActive, int groupId, String modelId) throws Exception;
	public Object putPortfolioConfiguration(String portfolioUuid,Boolean portfolioActive, Integer userId);

	public Object postPortfolio(MimeType inMimeType,MimeType outMimeType,String in,  int userId, int groupId, String modelId) throws Exception;
	public Object postPortfolioZip(MimeType mimeType, MimeType mimeType2,
			HttpServletRequest httpServletRequest, int userId, int groupId, String modelId) throws FileNotFoundException, IOException;

	public Object deletePortfolio(String portfolioUuid, int userId, int groupId) throws Exception;;

	/// Relatif aux mod�les
	public Object getModels(MimeType mimeType, int userId)throws Exception;
	public Object getModel(MimeType mimeType, Integer modelId, int userId) throws Exception;

	public Object postModels(MimeType mimeType, String xmlModel, int userId)throws Exception;

	/// Relatif aux noeuds
	public Object getNode(MimeType outMimeType,String nodeUuid,boolean withChildren, int userId, int groupId, String label) throws Exception;
	public Object getNodes(MimeType outMimeType, String portfolioUuid,int userId, int groupId, String semtag,String parentUuid, String filterId,String filterParameters,String sortId) throws Exception;
	public Object getNodes(MimeType mimeType, String portfoliocode, String semtag, int userId, int groupId, String semtag_parent, String code_parent) throws SQLException;
	public Object getNodeBySemanticTag(MimeType mimeType, String portfolioUuid, String semantictag, int userId, int groupId) throws Exception;
	Object getNodesBySemanticTag(MimeType outMimeType, int userId, int groupId, String portfolioUuid, String semanticTag) throws SQLException;
	public Object getNodeWithXSL(MimeType mimeType, String nodeUuid, String xslFile, int userId, int groupId);
	public Object getNodesParent(MimeType mimeType, String portfoliocode, String semtag, int userId, int groupId, String semtag_parent, String code_parent) throws Exception;
	public Object getNodeMetadataWad(MimeType mimeType, String nodeUuid, boolean b, int userId, int groupId, String label) throws SQLException;

	public Object putNode(MimeType inMimeType,String nodeUuid,String in, int userId, int groupId) throws Exception;
	public Object putNodeMetadata(MimeType mimeType, String nodeUuid, String xmlNode, int userId, int groupId) throws Exception ;
	public Object putNodeMetadataWad(MimeType mimeType, String nodeUuid, String xmlNode, int userId, int groupId) throws Exception;
	public Object putNodeNodeContext(MimeType mimeType, String nodeUuid, String xmlNode, int userId, int groupId)throws Exception;
	public Object putNodeNodeResource(MimeType mimeType, String nodeUuid, String xmlNode, int userId, int groupId)throws Exception;

	public Object postNode(MimeType inMimeType,String parentNodeUuid,String in, int userId, int groupId) throws Exception;
	public Object postNodeFromModelBySemanticTag(MimeType mimeType, String nodeUuid, String semantictag, int userId, int groupId) throws Exception;
	public Object postInstanciateNode(MimeType inMimeType,String portfolioUuid, String newCode, int userId, int groupId, boolean copyshared ) throws Exception;
	public Object postImportNode(MimeType inMimeType,String destUuid,String tag, String code, int userId, int groupId) throws Exception;

	public Object deleteNode(String nodeUuid, int userId, int groupId) throws Exception;

	/// Relatif aux ressources
	public Object getResource(MimeType outMimeType,String nodeParentUuid, int userId, int groupId) throws Exception;
	public Object getResources(MimeType outMimeType,String portfolioUuid, int userId, int groupId) throws Exception;

	public Object putResource(MimeType inMimeType,String nodeParentUuid,String in, int userId, int groupId) throws Exception;

	public Object postResource(MimeType inMimeType,String nodeParentUuid,String in,int userId, int groupId) throws Exception;

	public Object deleteResource(String resourceUuid, int userId, int groupId) throws Exception;

	/// Relatif aux utilisateurs
	public String getListUsers(int userId);
	public Object getUser(int userId) throws Exception;
	public String getUserID(int currentUser, String username);
	public String getInfUser(int userId, int userid2);
	public String getUsersByRole(int userId, String portfolioUuid, String role) throws SQLException;
	public String getUsersByGroup(int userId, int groupId) throws SQLException;

	public Object putUser(int userId,String oAuthToken,String oAuthSecret) throws Exception;
	public String putInfUser(int userId, int userid2, String xmlPortfolio) throws SQLException;

	public Object postUser(String xmluser, int userId) throws SQLException, Exception;
	public String postUsers(String xmlUsers, int userId)throws Exception;
	public String postUsersGroups(int userId);

	public Object deleteUser(int userid, int userId1);
	public Object deleteUsers(Integer userId, Integer userid2);

	/// Relatif aux fichiers
	public Object getFile(String nodeUuid,String lang);

	public Object putFile(String nodeUuid,String lang,String fileName,String destDirectory, String type,String extension,int size,byte[] fileBytes, int userId) throws Exception;

	/// Relatif aux ressources
	public String getResourceNodeUuidByParentNodeUuid(String nodeParentUuid);
	public String getRessource(String nodeUuid,int userId,int groupId, String type) throws SQLException;

	/// Relatif aux groupe d'utilisateurs
	public Object getUserGroups(int userId) throws Exception;
	public String getUserGroupByPortfolio(String portfolioUuid, int userId);
	public boolean isUserMemberOfGroup(int userId, int groupId);
	public String getGroupsUser(int userId, int userid2);
	public String getUsersByGroup(int userId);
	public String getGroupsByRole(int userId, String portfolioUuid, String role);
	public String getGroupsPortfolio(String portfolioUuid, int userId);
	public String getGroupList();
	public String getGroupUserList();

	public Integer putUserGroup(String siteGroupId, String userId);

	public Object postGroup(String xmlgroup, int userId) throws Exception ;
	public boolean postGroupsUsers(int userId, int groupId);
	public String postUsersGroupsUser(int userId, int usersgroup, int userid2);

	public String deleteUsersGroups(int userId, int usersgroup);
	public String deleteUsersGroupsUser(int userId, int usersgroup, int userid2);

	/// Relatif aux groupe de droits
	public String getPortfolioRights(int userId, String portfolio) throws Exception;
	public String getGroupRightsInfos(int userId, String portfolioId) throws SQLException;
	public String getRolePortfolio(MimeType mimeType, String portfolioId, int userId);
	String getRole(MimeType mimeType, int grid, int userId) throws SQLException;
	public String getGroupRightList();

	public Object putRole(String xmlRole, int userId, int roleId)throws Exception;

	public String postRoleUser(int userId, int grid, Integer userid2)throws SQLException;
	boolean postNodeRight(int userId, String nodeUuid, int grid, String xml);
	public boolean postRightGroup(int groupRightId, int groupId, Integer userId);

	public Object deleteGroupRights(Integer groupId, Integer groupRightId, Integer userId);

	/// À propos des macro-commandes pour la modification des droits
	/// e.g.: submit, show/hide; ainsi que la partie gestion de ces commandes
	public String postMacroOnNode(int userId, String nodeUuid, Integer macro);
	public String postAddAction(int userId, Integer macro, String role, String data);
	public Integer postCreateMacro(int userId, String macroName);

	public String getAllActionLabel(int userId);
	public String getMacroActions(int userId, Integer macro);
	public String getPortfolioMacro(int userId, String portfolioId);

	public String putMacroName(int userId, Integer macro, String name);
	public String putMacroAction(int userId, Integer macro, String role, String data);

	public String deleteMacro(int userId, Integer macro);
	public String deleteMacroAction(int userId, Integer macro, String role);

	/// À propos des types que l'on ajoute et les droits à créer lors d'une instanciation
	/// Aussi la partie gestion des types
	public String postCreateType(int userId, String name);
	public String postAddNodeType(int userId, Integer type, Integer nodeid, Integer parentid, Integer instance, String data);
	public String postUseType(int userId, String nodeUuid, Integer type);

	public String getAllTypes(int userId);
	public String getTypeData(int userId, Integer type);
	public String getPortfolioTypes(int userId, String portfolioId);

	public String putTypeName(int userId, Integer type, String name);
	public String putTypeData(int userId, Integer type, Integer nodeid, Integer parentid, Integer instance, String data);

	public String deleteType(int userId, Integer type);
	public String deleteTypeNode(int userId, Integer type, Integer nodeid);

	/// À propos de la gestion des groupes de droits
	public String getRRGList(int userId, String portfolio, Integer user, String role);
	public String getRRGInfo(int userId, Integer rrgid);
	public String getPortfolioInfo(int userId, String portId);

	public String putRRGUpdate(int userId, Integer rrgId, String data);

	public String postRRGCreate(int userId, String data);
	public String postRRGUsers( int userId, Integer rrgid, String data );
	public String postRRGUser(int userId, Integer rrgid, Integer user);

	public String deleteRRG(int userId, Integer rrgId);
	public String deleteRRGUser(int userId, Integer rrgId, Integer user);
	public String deletePortfolioUser( int userId, String portId );

	//// LTI related
	public String getUserId(String username) throws Exception;
	public String createUser(String username) throws Exception;
	public String getGroupByName( String name );
	public String createGroup( String name );
	public boolean isUserInGroup( String uid, String gid );

}
