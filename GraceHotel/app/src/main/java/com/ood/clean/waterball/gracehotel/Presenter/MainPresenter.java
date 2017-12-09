package com.ood.clean.waterball.gracehotel.Presenter;

import com.ood.clean.waterball.gracehotel.Model.UserRepository;
import com.ood.clean.waterball.gracehotel.Model.datamodel.User;
import com.ood.clean.waterball.gracehotel.Threading.ThreadExecutor;
import com.ood.clean.waterball.gracehotel.View.MainView;

public class MainPresenter {
	private ThreadExecutor threadExecutor;
	private UserRepository userRepository;
	private MainView mainView;


	public MainPresenter(ThreadExecutor threadExecutor, UserRepository userRepository) {
		this.threadExecutor = threadExecutor;
		this.userRepository = userRepository;
	}

	public void setMainView(MainView mainView) {
		this.mainView = mainView;
	}

	public void signIn(final String roomNumber) {
		threadExecutor.execute(new Runnable() {
			@Override
			public void run() {
				User user = userRepository.createUser(roomNumber);
				sendUserToMainView(user);
			}
		});
	}

	public boolean hasUser(){
		return userRepository.userExists();
	}

	public void getUser(){
		threadExecutor.execute(new Runnable() {
			@Override
			public void run() {
				final User user = userRepository.getUser();
				sendUserToMainView(user);
			}
		});
	}

	private void sendUserToMainView(final User user){
		threadExecutor.executeOnMainThread(new Runnable() {
			@Override
			public void run() {
				mainView.onSignInSucessfully(user);
			}
		});
	}

}
