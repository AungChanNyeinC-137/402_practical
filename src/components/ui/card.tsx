import type { ComponentProps } from 'react'

type CardProps = ComponentProps<'div'>

export function Card({ className = '', ...props }: CardProps) {
  return <div className={`rounded-xl border border-border bg-card text-card-foreground shadow-sm ${className}`} {...props} />
}
