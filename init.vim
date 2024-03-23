" custom init.vim config for VS Code Neovim Extension
set number
set relativenumber
set autoindent
set tabstop=4
set shiftwidth=4
set smarttab
set softtabstop=0
set mouse=a
set clipboard+=unnamedplus
set autochdir

" QOL Mappings
let mapleader = " "
nnoremap ; :
nnoremap <leader>11 :LspDocumentFormat<CR>
vnoremap ; :
vnoremap <Leader>pa :s/\\/\//g<CR>

" All other Extensions are handled by VS Code, unnecessary here!
