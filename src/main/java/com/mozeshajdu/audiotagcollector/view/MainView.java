package com.mozeshajdu.audiotagcollector.view;

import de.saxsys.mvvmfx.FxmlView;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MainView implements FxmlView<MainViewModel> {

}
