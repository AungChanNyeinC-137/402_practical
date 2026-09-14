import type { ComponentProps } from 'react'

type ButtonProps = ComponentProps<'a'>

export function Button({ className = '', ...props }: ButtonProps) {
  return <a className={`inline-flex size-9 shrink-0 items-center justify-center rounded-md text-muted-foreground transition-colors hover:bg-muted hover:text-foreground focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:outline-primary ${className}`} {...props} />
}
