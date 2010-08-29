package ro.redeul.google.go.actions;

import com.intellij.facet.FacetManager;
import com.intellij.ide.actions.CreateFileFromTemplateDialog;
import com.intellij.ide.actions.CreateTemplateInPackageAction;
import com.intellij.openapi.actionSystem.DataContext;
import com.intellij.openapi.actionSystem.DataKeys;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.module.ModuleManager;
import com.intellij.openapi.project.DumbAware;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.roots.ModuleRootManager;
import com.intellij.openapi.roots.ModuleRootModel;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiDirectory;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.util.IncorrectOperationException;
import org.jetbrains.annotations.NotNull;
import ro.redeul.google.go.GoBundle;
import ro.redeul.google.go.GoFileType;
import ro.redeul.google.go.GoIcons;
import ro.redeul.google.go.config.facet.GoFacetType;
import ro.redeul.google.go.lang.psi.GoFile;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * User: mtoader
 * Date: Aug 20, 2010
 * Time: 11:41:58 PM
 * To change this template use File | Settings | File Templates.
 */
public class NewGoLibraryAction extends CreateTemplateInPackageAction<GoFile> implements DumbAware {

    public NewGoLibraryAction() {
        super(GoBundle.message("new.go.lib"), GoBundle.message("new.go.lib.description"), GoIcons.GO_ICON_16x16, true);
    }

    @Override
    protected PsiElement getNavigationElement(@NotNull GoFile file) {
        return file;
    }

    @Override
    protected boolean checkPackageExists(PsiDirectory directory) {
        return true;
    }

    @Override
    protected boolean isAvailable(DataContext dataContext) {
        return super.isAvailable(dataContext)
                && hasGoFacet(DataKeys.MODULE.getData(dataContext));
    }

    private boolean hasGoFacet(Module module) {        
        return FacetManager.getInstance(module).getFacetByType(GoFacetType.GO_FACET_TYPE_ID) != null;
    }

    @Override
    protected void doCheckCreate(PsiDirectory dir, String parameterName, String typeName) throws IncorrectOperationException {
        // check to see if a file with the same name already exists

        String fileName = fileNameFromTypeName(typeName, parameterName);

        VirtualFile targetFile = dir.getVirtualFile().findFileByRelativePath(fileName);
        if  ( targetFile != null ) {
            throw new IncorrectOperationException(GoBundle.message("target.file.exists", targetFile.getPath()));
        }
    }

    @Override
    protected GoFile doCreate(PsiDirectory dir, String parameterName, String typeName) throws IncorrectOperationException {
        GoTemplatesFactory.Template template = GoTemplatesFactory.Template.GoFile;

        String fileName = fileNameFromTypeName(typeName, parameterName);
        String packageName = packageNameFromTypeName(typeName, parameterName);

        if ( typeName.equals("multiple") ) {
            if ( dir.findSubdirectory(parameterName) == null ) {
                dir = dir.createSubdirectory(parameterName);
            } else {
                dir = dir.findSubdirectory(parameterName);
            }

            fileName = fileName.replaceFirst(parameterName + "/", "");
        }

        return GoTemplatesFactory.createFromTemplate(dir, packageName, fileName, template);
    }

    String fileNameFromTypeName(String typeName, String parameterName) {
        if ( typeName.startsWith("lib.") ) {
            return parameterName + ".go";
        }

        if ( typeName.equals("multiple") ) {
            return parameterName + "/" + parameterName + ".go";
        }

        return parameterName + ".go";
    }

    String packageNameFromTypeName(String typeName, String parameterName) {

        if ( typeName.startsWith("lib.") ) {
            return typeName.replaceFirst("^lib\\.", "");
        }

        return parameterName;
    }

    @Override
    protected void buildDialog(Project project, PsiDirectory directory, CreateFileFromTemplateDialog.Builder builder) {
        builder.setTitle("New Go file creation");

        PsiFile childs[] = directory.getFiles();

        Set<String> packages = new HashSet<String>();

        for (PsiFile child : childs) {
            if (child instanceof GoFile) {
                GoFile goFile = (GoFile) child;

                if ( ! goFile.getPackage().isMainPackage() ) {
                    packages.add(goFile.getPackage().getPackageName());
                }
            }
        }

        for (String packageName : packages) {
            builder.addKind("New: " + packageName + " library file", GoIcons.GO_ICON_16x16, "lib." + packageName);
        }

        builder.addKind("New library (child folder)", GoIcons.GO_ICON_16x16, "multiple");
        builder.addKind("New library (this folder)", GoIcons.GO_ICON_16x16, "single");
    }

    private boolean isLibraryFolder(PsiDirectory directory) {
        return false;
    }

    private boolean isApplicationFolder(PsiDirectory directory) {
        return false;
    }

    @Override
    protected String getActionName(PsiDirectory directory, String newName, String templateName) {
        return GoBundle.message("new.go.lib.action.text");
    }
}
