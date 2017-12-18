package com.ood.clean.waterball.gracehotel.Presenter;

import com.ood.clean.waterball.gracehotel.Model.UserRepository;
import com.ood.clean.waterball.gracehotel.Model.datamodel.User;
import com.ood.clean.waterball.gracehotel.Model.sprite.SpritePrototypeFactory;
import com.ood.clean.waterball.gracehotel.Threading.ThreadExecutor;
import com.ood.clean.waterball.gracehotel.View.MainView;

public class MainPresenter {
	private ThreadExecutor threadExecutor;
	private UserRepository userRepository;
	private MainView mainView;


	public MainPresenter(ThreadExecutor threadExecutor,
						 UserRepository userRepository) {
		this.threadExecutor = threadExecutor;
		this.userRepository = userRepository;
	}

	public void setMainView(MainView mainView) {
		this.mainView = mainView;
	}

	public void signIn(final String roomNumber) {
		threadExecutor.execute(()->{
			delay(2000);
			preparePrototypes();
			delay(2000);
			User user = userRepository.createUser(roomNumber);
			arrangeGameItems(user);
			delay(2000);
			threadExecutor.executeOnMainThread(()->mainView.onSignInSucessfully(user));
		});
	}

	private void delay(int mtimes){
		try {
			Thread.sleep(mtimes);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public boolean hasLocalUser(){
		return userRepository.userExists();
	}

	public void loadLocalUser(){
		threadExecutor.execute(()->{
				preparePrototypes();
				final User user = userRepository.getUser();
				threadExecutor.executeOnMainThread(()->mainView.onSignInSucessfully(user));
		});
	}

	private void arrangeGameItems(User user){
		threadExecutor.executeOnMainThread(()->mainView.onGameItemArrangementCompleted());
	}

	private void preparePrototypes(){
		SpritePrototypeFactory.getInstance().preparePrototypes();
		threadExecutor.executeOnMainThread(()->mainView.onPrototypePreparedCompleted());
	}

}
