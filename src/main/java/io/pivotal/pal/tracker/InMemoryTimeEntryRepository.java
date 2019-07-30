package io.pivotal.pal.tracker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class InMemoryTimeEntryRepository implements TimeEntryRepository {

	private HashMap<Long, TimeEntry> map = new HashMap();
	private Long timeEntryID = 1L;
	
	@Override
	public TimeEntry create(TimeEntry timeEntryToCreate) {
		long currentID = timeEntryID++;
		TimeEntry timeEntry = new TimeEntry(currentID, timeEntryToCreate.getProjectId(), 
				timeEntryToCreate.getUserId(), timeEntryToCreate.getDate(), timeEntryToCreate.getHours());
		map.put(currentID, timeEntry);
		return timeEntry;
	}

	@Override
	public List<TimeEntry> list() {
		
		return new ArrayList<TimeEntry>(map.values());
	}

	@Override
	public TimeEntry update(Long timeID, TimeEntry timeEntry) {
		
		TimeEntry timeEntryToUpdate = map.get(timeID);
		if(null == timeEntryToUpdate) {
			return null;
		}
		
		timeEntryToUpdate.setProjectId(timeEntry.getProjectId());
		timeEntryToUpdate.setUserId(timeEntry.getUserId());
		timeEntryToUpdate.setDate(timeEntry.getDate());
		timeEntryToUpdate.setHours(timeEntry.getHours());
		return timeEntryToUpdate;
	}

	@Override
	public void delete(Long timeID) {
		
		map.remove(timeID);
		
	}

	@Override
	public TimeEntry find(Long timeID) {
		TimeEntry timeEntryToFind = map.get(timeID);
		return timeEntryToFind;
	}

}
