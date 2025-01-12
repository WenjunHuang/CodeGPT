package ee.carlrobert.codegpt.toolwindow.conversations.actions;

import static icons.Icons.DefaultImageIcon;

import com.intellij.icons.AllIcons;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.ui.Messages;
import ee.carlrobert.codegpt.state.conversations.ConversationsState;
import org.jetbrains.annotations.NotNull;

public class DeleteConversationAction extends AnAction {

  private final Runnable onRefresh;

  public DeleteConversationAction(Runnable onRefresh) {
    super("Delete Conversation", "Delete single conversation", AllIcons.General.Remove);
    this.onRefresh = onRefresh;
  }

  @Override
  public void update(@NotNull AnActionEvent event) {
    event.getPresentation().setEnabled(ConversationsState.getCurrentConversation() != null);
  }

  @Override
  public void actionPerformed(@NotNull AnActionEvent e) {
    int answer = Messages.showYesNoDialog("Are you sure you want to delete this conversation?", "Delete Converation", DefaultImageIcon);
    if (answer == Messages.YES) {
      ConversationsState.getInstance().deleteSelectedConversation();
      onRefresh.run();
    }
  }
}
