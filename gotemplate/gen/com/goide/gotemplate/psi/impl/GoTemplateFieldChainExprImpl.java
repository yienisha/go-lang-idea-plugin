// This is a generated file. Not intended for manual editing.
package com.goide.gotemplate.psi.impl;

import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElementVisitor;
import com.goide.gotemplate.psi.*;

public class GoTemplateFieldChainExprImpl extends GoTemplateExpressionImpl implements GoTemplateFieldChainExpr {

  public GoTemplateFieldChainExprImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof GoTemplateVisitor) ((GoTemplateVisitor)visitor).visitFieldChainExpr(this);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public GoTemplateExpression getExpression() {
    return findChildByClass(GoTemplateExpression.class);
  }

  @Override
  @NotNull
  public GoTemplateFieldChain getFieldChain() {
    return findNotNullChildByClass(GoTemplateFieldChain.class);
  }

}
