package org.nibiru.mobile.mgwt.ui.place;

import com.google.gwt.place.shared.Place;
import com.googlecode.mgwt.mvp.client.AnimationMapper;
import com.googlecode.mgwt.ui.client.widget.animation.Animation;
import com.googlecode.mgwt.ui.client.widget.animation.Animations;

import org.nibiru.mobile.gwt.ui.place.SimplePlace;

public class DefaultAnimationMapper implements AnimationMapper {

	@Override
	public Animation getAnimation(Place oldPlace, Place newPlace) {
		int anim = (int) (Math.random() * 6);

		boolean forward = true;
		if (oldPlace instanceof SimplePlace && newPlace instanceof SimplePlace) {
			forward = ((SimplePlace) newPlace)
					.forwardFrom((SimplePlace) oldPlace);
		}

		if (forward) {
			switch (anim) {
			case 0:
				return Animations.SLIDE;
			case 1:
				return Animations.FLIP;
			case 2:
				return Animations.DISSOLVE;
			case 3:
				return Animations.POP;
			case 4:
				return Animations.SWAP;
			case 5:
				return Animations.FADE;
			default:
				return Animations.SLIDE;
			}
		} else {
			switch (anim) {
			case 0:
				return Animations.SLIDE_REVERSE;
			case 1:
				return Animations.FLIP_REVERSE;
			case 2:
				return Animations.DISSOLVE_REVERSE;
			case 3:
				return Animations.POP_REVERSE;
			case 4:
				return Animations.SWAP_REVERSE;
			case 5:
				return Animations.FADE_REVERSE;
			default:
				return Animations.SLIDE_UP_REVERSE;
			}
		}
	}

}
