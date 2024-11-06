package se.isselab.HAnS.states;

import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;


import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

@State(
        name = "ToggleState",
        storages = {@Storage("ToggleState.xml")}
)
public class ToggleStateService implements PersistentStateComponent<ToggleState> {

    private final PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);
    private ToggleState state = new ToggleState();

    public static ToggleStateService getInstance() {
        return com.intellij.openapi.application.ApplicationManager.getApplication().getService(ToggleStateService.class);
    }

    @Nullable
    @Override
    public ToggleState getState() {
        return state;
    }

    @Override
    public void loadState(@NotNull ToggleState state) {
        this.state = state;
    }

    public boolean isAnnotationsVisible() {
        return state.isEnabled;
    }

    public void setAnnotationsVisible(boolean visible) {
        boolean oldVisible = state.isEnabled;
        state.isEnabled = visible;
        changeSupport.firePropertyChange("annotationsVisible", oldVisible, visible);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
}
