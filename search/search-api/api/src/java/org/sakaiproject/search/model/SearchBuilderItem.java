/**********************************************************************************
 *
 * $Header$
 *
 ***********************************************************************************
 *
 * Copyright (c) 2005 University of Cambridge
 * 
 * Licensed under the Educational Community License Version 1.0 (the "License");
 * By obtaining, using and/or copying this Original Work, you agree that you have read,
 * understand, and will comply with the terms and conditions of the Educational Community License.
 * You may obtain a copy of the License at:
 * 
 *      http://cvs.sakaiproject.org/licenses/license_1_0.html
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED,
 * INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE
 * AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
 * DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING 
 * FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 *
 **********************************************************************************/
package org.sakaiproject.search.model;

import java.util.Date;

/**
 * Represents an operation or stat of a document in the search engine. This
 * Object is used as a communication and persisntance mechanism between the
 * changes made to entities and the thread processing the indec updates
 * 
 * @author ieb
 */
public interface SearchBuilderItem
{
	String getId();
	void setId(String id);
	/**
	 * Name of the resource in the search index
	 * 
	 * @return
	 */
	String getName();

	/**
	 * The name of the resource in the search index
	 * 
	 * @param name
	 */
	void setName(String name);

	/**
	 * A master record is used to override the indexer threa operation and avoid
	 * hude updates to the database in the request cycle.
	 */
	public static final String INDEX_MASTER = "_master_control";

	/**
	 * The action being performent
	 * 
	 * @return
	 */
	Integer getSearchaction();

	/**
	 * The action being performed
	 * 
	 * @param action
	 */
	void setSearchaction(Integer searchaction);

	/**
	 * Action Unknown, usually becuase the record has just been created
	 */
	public static final Integer ACTION_UNKNOWN = new Integer(0);

	/**
	 * Action ADD the record to the search engine, if the doc ID is set, then
	 * remove first, if not set, check its not there.
	 */
	public static final Integer ACTION_ADD = new Integer(1);

	/**
	 * Action DELETE the record from the search engine, once complete delete the
	 * record
	 */
	public static final Integer ACTION_DELETE = new Integer(2);

	/**
	 * The action REBUILD causes the indexer thread to rebuild the index from
	 * scratch, refetching all entities This sould only ever appear on the
	 * master record
	 */
	public static final Integer ACTION_REBUILD = new Integer(11);

	/**
	 * The action REFRESH causes the indexer thread to refresh the search index
	 * from the current set of entities. If a Rebuild is in progress, the
	 * refresh will not overrise the rebuild
	 */
	public static final Integer ACTION_REFRESH = new Integer(10);

	/**
	 * The state of the record
	 * 
	 * @return
	 */
	Integer getSearchstate();

	/**
	 * The state of the record
	 * 
	 * @param state
	 */
	void setSearchstate(Integer searchstate);

	/**
	 * Unknown state
	 */
	public static final Integer STATE_UNKNOWN = new Integer(0);

	/**
	 * Operation pending
	 */
	public static final Integer STATE_PENDING = new Integer(1);

	/**
	 * Operation completed
	 */
	public static final Integer STATE_COMPLETED = new Integer(2);

	public static final Integer STATE_PENDING_2 = new Integer(3);

	/**
	 * The last update to the record
	 * 
	 * @return
	 */
	Date getVersion();

	/**
	 * The last update to the record
	 * 
	 * @param lastupdate
	 */
	void setVersion(Date version);
	
	/**
	 * The context of the index item
	 * @return
	 */
	String getContext();
	/**
	 * The context of the index item
	 * @param context
	 */
	void setContext(String context);
	
	public static final String GLOBAL_CONTEXT = "global";

	public static final String GLOBAL_MASTER = SearchBuilderItem.INDEX_MASTER+"_"+SearchBuilderItem.GLOBAL_CONTEXT;
	
	public static final String SITE_MASTER_FORMAT = SearchBuilderItem.INDEX_MASTER+"_{0}";

	public static final String SITE_MASTER_PATTERN = SearchBuilderItem.INDEX_MASTER+"_%";

	public static final String[] states = new String[] {
		"U",
		"P",
		"P2",
		"C"
	};
	public static final String[] actions = new String[] {
		"U",
		"A",
		"D",
		"-",
		"-",
		"-",
		"-",
		"-",
		"-",
		"-",
		"RF",
		"RB"
	};
	

}
