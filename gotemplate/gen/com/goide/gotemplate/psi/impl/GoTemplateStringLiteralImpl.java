// This is a generated file. Not intended for manual editing.
package com.goide.gotemplate.psi.impl;

import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.goide.gotemplate.psi.*;

public class GoTemplateStringLiteralImpl extends ASTWrapperPsiElement implements GoTemplateStringLiteral {

  public GoTemplateStringLiteralImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof GoTemplateVisitor) ((GoTemplateVisitor)visitor).visitStringLiteral(this);
    else super.accept(visitor);
  }

}
