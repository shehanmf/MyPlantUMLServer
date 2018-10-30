package org.smf.tools;

import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.directory.*;
import java.util.Hashtable;

/**
 * @author shehan.fernando
 */
public class LdapTest
{

  static DirContext ldapContext;
  public static void main(String[] args)
  {
    try
    {
      System.out.println("Starting Active Directory");

      Hashtable<String, String> ldapEnv = new Hashtable<String, String>(11);
      ldapEnv.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");

      ldapEnv.put(Context.PROVIDER_URL,  "ldap://cslk-ad3.cambio.se:389");
      ldapEnv.put(Context.SECURITY_AUTHENTICATION, "simple");

      ldapEnv.put(Context.SECURITY_PRINCIPAL, "shehan.fernando@cambio.lk");
      ldapEnv.put(Context.SECURITY_CREDENTIALS, "5tgb@WSX");
      //ldapEnv.put(Context.SECURITY_PROTOCOL, "ssl");
      //ldapEnv.put(Context.SECURITY_PROTOCOL, "simple");
      ldapContext = new InitialDirContext(ldapEnv);

      // Create the search controls
      SearchControls searchCtls = new SearchControls();

      //Specify the attributes to return
      String returnedAtts[]={"sn","givenName", "samAccountName"};
      searchCtls.setReturningAttributes(returnedAtts);

      //Specify the search scope
      searchCtls.setSearchScope(SearchControls.SUBTREE_SCOPE);

      //specify the LDAP search filter
      String searchFilter = "(&(objectClass=user))";

      //Specify the Base for the search
      String searchBase = "OU=Employees,OU=Cambio,DC=cambio,DC=se";
      //initialize counter to total the results
      int totalResults = 0;

      // Search for objects using the filter
      NamingEnumeration<SearchResult> answer = ldapContext.search(searchBase, searchFilter, searchCtls);

      //Loop through the search results
      while (answer.hasMoreElements())
      {
        SearchResult sr = (SearchResult)answer.next();

        totalResults++;

        System.out.println(">>>" + sr.getName());
        Attributes attrs = sr.getAttributes();
        System.out.println(">>>>>>" + attrs.get("samAccountName"));
      }

      System.out.println("Total results: " + totalResults);
      ldapContext.close();
    }
    catch (Exception e)
    {
      System.out.println(" Search error: " + e);
      e.printStackTrace();
      System.exit(-1);
    }
  }
}
