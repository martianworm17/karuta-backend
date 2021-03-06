Karuta in Sakai via LTI 1.x
=

Tested with Sakai 10.6

## Karuta side

1\. Configure the following lines in *configKaruta.properties*

    # ==== LTI configuration =====
    ### Uncomment and configure when you want to use LTI
    #basiclti.provider.{KEY}.secret={SECRET}
    ### Redirection for Karuta 1.2.x
    #lti_redirect_location=/karuta/karuta/htm/list.htm
    ### Redirection for Karuta 2.x
    lti_redirect_location=/karuta/application/htm/karuta.htm

You can change *lti\_redirect\_location* to something else if the UI is on another server, or under a different name

## Sakai side

### As a site-wide tool
TODO

### Per-course basis

1\. Editing the worksite

Select the worksite you want to add Karuta to.

![Edit worksite](./lti_screenshot/01-edit_worksite_b.png)

2\. Edit tools

Edit the tools that it contains

![Edit tools](./lti_screenshot/02-edit_tools_b.png)

3\. Select tool

Select 'External tools' and continue.

![Select tool](./lti_screenshot/03-select_tool_b.png)

4\. Add tool

Add a title, continue.

![Add tool](./lti_screenshot/04-add_tool_b.png)

5\. Finish add tool

Finish.

![Finish add tool](./lti_screenshot/05-finish_add_tool_c.png)

6\. Configure tool

Inside the worksite, configure the tool

![Configure tool](./lti_screenshot/06-configure_tool_b.png)

7\. Finish configure tool

Input the remote tool information (the same than in configKaruta.properties) and check the following checkboxes

![Finish configure tool](./lti_screenshot/07-finish_configure_tool_b.png)

8\. LTI view

The system will log you as the username from Sakai inside Karuta. You can prepare the portfolios and accounts using the batch tools.

![LTI view](./lti_screenshot/08-lti_view_b.png)

9\. Showing the debug view

When checking the debug view, the version shows 'LTI-1p0' 

![Debug](./lti_screenshot/09-debug_c.png)

## Side note
You might have an empty page when first logging in from Sakai to Karuta, for this you can go as *admin* or an *editor* and create and share a portfolio with the user.