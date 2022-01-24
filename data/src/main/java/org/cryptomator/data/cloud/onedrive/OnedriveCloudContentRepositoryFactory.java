package org.cryptomator.data.cloud.onedrive;

import static org.cryptomator.domain.CloudType.ONEDRIVE;

import android.content.Context;

import org.cryptomator.data.repository.CloudContentRepositoryFactory;
import org.cryptomator.domain.Cloud;
import org.cryptomator.domain.OnedriveCloud;
import org.cryptomator.domain.repository.CloudContentRepository;
import org.cryptomator.util.SharedPreferencesHandler;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class OnedriveCloudContentRepositoryFactory implements CloudContentRepositoryFactory {

	private final Context context;
	private final SharedPreferencesHandler sharedPreferencesHandler;

	@Inject
	public OnedriveCloudContentRepositoryFactory(Context context, SharedPreferencesHandler sharedPreferencesHandler) {
		this.context = context;
		this.sharedPreferencesHandler = sharedPreferencesHandler;
	}

	@Override
	public boolean supports(Cloud cloud) {
		return cloud.type() == ONEDRIVE;
	}

	@Override
	public CloudContentRepository<OnedriveCloud, OnedriveNode, OnedriveFolder, OnedriveFile> cloudContentRepositoryFor(Cloud cloud) {
		OnedriveCloud onedriveCloud = (OnedriveCloud) cloud;
		return new OnedriveCloudContentRepository(onedriveCloud, context, OnedriveClientFactory.Companion.createInstance(context, onedriveCloud.accessToken(), sharedPreferencesHandler));
	}
}
