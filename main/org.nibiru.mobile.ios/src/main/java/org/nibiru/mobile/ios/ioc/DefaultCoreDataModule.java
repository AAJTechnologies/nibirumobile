package org.nibiru.mobile.ios.ioc;

import static com.google.common.base.Preconditions.checkNotNull;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import ios.coredata.NSManagedObjectContext;
import ios.coredata.NSManagedObjectModel;
import ios.coredata.NSPersistentStoreCoordinator;
import ios.foundation.NSArray;
import ios.foundation.NSBundle;
import ios.foundation.NSFileManager;
import ios.foundation.NSURL;
import ios.foundation.enums.NSSearchPathDirectory;
import ios.foundation.enums.NSSearchPathDomainMask;

@Module
public class DefaultCoreDataModule {

    @Provides
    @Singleton
    public NSManagedObjectModel getNSManagedObjectModel() {
        return NSManagedObjectModel.alloc().initWithContentsOfURL(NSURL.fileURLWithPath(NSBundle.mainBundle()
                .pathForResourceOfType("dataModel", "mom")));
    }

    @Provides
    @Singleton
    public NSPersistentStoreCoordinator getNSPersistentStoreCoordinator(NSManagedObjectModel managedObjectModel) {
        checkNotNull(managedObjectModel);
        NSPersistentStoreCoordinator persistentStoreCoordinator = NSPersistentStoreCoordinator.alloc().initWithManagedObjectModel(
                managedObjectModel);
        NSArray<NSURL> nsa = (NSArray<NSURL>) NSFileManager.defaultManager().URLsForDirectoryInDomains(
                NSSearchPathDirectory.DocumentDirectory, NSSearchPathDomainMask.UserDomainMask);
        NSURL nsu = nsa.get(0).URLByAppendingPathComponent("dataStore.sqlite");
        persistentStoreCoordinator.addPersistentStoreWithTypeConfigurationURLOptionsError("SQLite", null, nsu, null, null);
        return persistentStoreCoordinator;
    }

    @Provides
    @Singleton
    public NSManagedObjectContext getNSManagedObjectContext(
            NSPersistentStoreCoordinator persistentStoreCoordinator) {
        checkNotNull(persistentStoreCoordinator);
        NSManagedObjectContext managedObjectContext = NSManagedObjectContext.alloc().init();
        managedObjectContext
                .setPersistentStoreCoordinator(persistentStoreCoordinator);
        return managedObjectContext;
    }
}
