package org.nibiru.mobile.ios.ioc;

import static com.google.common.base.Preconditions.checkNotNull;

import javax.inject.Singleton;

import org.robovm.apple.coredata.NSManagedObjectContext;
import org.robovm.apple.coredata.NSManagedObjectModel;
import org.robovm.apple.coredata.NSPersistentStoreCoordinator;
import org.robovm.apple.coredata.NSPersistentStoreType;
import org.robovm.apple.foundation.NSArray;
import org.robovm.apple.foundation.NSBundle;
import org.robovm.apple.foundation.NSErrorException;
import org.robovm.apple.foundation.NSFileManager;
import org.robovm.apple.foundation.NSSearchPathDirectory;
import org.robovm.apple.foundation.NSSearchPathDomainMask;
import org.robovm.apple.foundation.NSURL;

import dagger.Module;
import dagger.Provides;

@Module
public class DefaultCoreDataModule {

	@Provides
	@Singleton
	public NSManagedObjectModel getNSManagedObjectModel() {
		return new NSManagedObjectModel(NSBundle.getMainBundle()
				.findResourceURL("dataModel", "mom"));
	}

	@Provides
	@Singleton
	public NSPersistentStoreCoordinator getNSPersistentStoreCoordinator(NSManagedObjectModel managedObjectModel) {
		checkNotNull(managedObjectModel);
		try {
			NSPersistentStoreCoordinator persistentStoreCoordinator = new NSPersistentStoreCoordinator(
					managedObjectModel);
			NSArray<NSURL> nsa = NSFileManager.getDefaultManager().getURLsForDirectory(
					NSSearchPathDirectory.DocumentDirectory, NSSearchPathDomainMask.UserDomainMask);
			NSURL nsu = nsa.get(0).newURLByAppendingPathComponent("dataStore.sqlite");
			persistentStoreCoordinator.addPersistentStore(NSPersistentStoreType.SQLite, null, nsu, null);
			return persistentStoreCoordinator;
		} catch (NSErrorException e) {
			throw new RuntimeException(e);
		}
	}

	@Provides
	@Singleton
	public NSManagedObjectContext getNSManagedObjectContext(
			NSPersistentStoreCoordinator persistentStoreCoordinator) {
		checkNotNull(persistentStoreCoordinator);
		NSManagedObjectContext managedObjectContext = new NSManagedObjectContext();
		managedObjectContext
				.setPersistentStoreCoordinator(persistentStoreCoordinator);
		return managedObjectContext;
	}
}
