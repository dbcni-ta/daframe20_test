function initWiseGrid(objName, width, height)
{
/* EUC-KR */

/*
	var WISEGRID_TAG = "<OBJECT ID='" + objName + "' codebase='../cab/WiseGrid_v5212.cab#version=5,2,1,2'";
	WISEGRID_TAG     = WISEGRID_TAG + " NAME='" + objName + "' WIDTH=" + width + " HEIGHT=" + height + " border=0";
	WISEGRID_TAG     = WISEGRID_TAG + " CLASSID='CLSID:E8AA1760-8BE5-4891-B433-C53F7333710F'>";
	WISEGRID_TAG     = WISEGRID_TAG + " <PARAM NAME = 'strLicenseKeyList' VALUE='122670CA8BD07EF9CD21AC63B6EB6E08'>";
	WISEGRID_TAG     = WISEGRID_TAG + "</OBJECT>";
*/

	/* UTF-8 */
	var WISEGRID_TAG = "<OBJECT ID='" + objName + "' codebase='../cab/WiseGridU_v5212.cab#version=5,2,1,2'";
	WISEGRID_TAG     = WISEGRID_TAG + " NAME='" + objName + "' WIDTH=" + width + " HEIGHT=" + height + " border=0";
	WISEGRID_TAG     = WISEGRID_TAG + " CLASSID='CLSID:0CE50171-51F4-4b1e-992B-4ECC8E0BE537'>";
	WISEGRID_TAG     = WISEGRID_TAG + " <PARAM NAME = 'strLicenseKeyList' VALUE='8BA4276E9CF2E8B2BBA5F636F384FD4A'>";
	WISEGRID_TAG     = WISEGRID_TAG + "</OBJECT>"
	document.write(WISEGRID_TAG);
}





function setContextMenu(GridObj){
	GridObj.bContextMenuVisible = true;
	GridObj.bUseDefaultContextMenu = false ;
	GridObj.bUserContextMenu = true ;
	
	
	GridObj.AddDefaultContextMenuItem('MENUITEM_CELL_COPY');
	GridObj.AddContextMenuSeparator('MENU_CELL');
	GridObj.AddDefaultContextMenuItem('MENUITEM_CELL_PASTE');
	GridObj.AddContextMenuSeparator('MENU_CELL');
	GridObj.AddDefaultContextMenuItem('MENUITEM_CELL_EXCELEXPORT');
	GridObj.AddDefaultContextMenuItem('MENUITEM_CELL_FONTUP');
	GridObj.AddContextMenuSeparator('MENU_CELL');
	GridObj.AddDefaultContextMenuItem('MENUITEM_CELL_FONTDOWN');
	GridObj.AddDefaultContextMenuItem('MENUITEM_CELL_FIND');
	GridObj.AddContextMenuSeparator('MENU_CELL');
	
	GridObj.AddContextMenuSeparator('MENU_HEADER');
	GridObj.AddDefaultContextMenuItem('MENUITEM_HD_HIDEHEADER');
	GridObj.AddContextMenuSeparator('MENU_HEADER');
	GridObj.AddDefaultContextMenuItem('MENUITEM_HD_CANCELHIDEHEADER');
	GridObj.AddDefaultContextMenuItem('MENUITEM_HD_FIXHEADER');
	GridObj.AddContextMenuSeparator('MENU_HEADER');
	GridObj.AddDefaultContextMenuItem('MENUITEM_HD_CANCELFIXHEADER');

}
